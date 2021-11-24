package ru.example.projectmanagement.services.specifications;

import org.springframework.data.jpa.domain.Specification;
import ru.example.projectmanagement.dto.TaskFilterDTO;
import ru.example.projectmanagement.entities.Task;
import ru.example.projectmanagement.entities.enums.Status;
import ru.example.projectmanagement.entities.enums.Type;
import ru.example.projectmanagement.exceptions.BadRequestException;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class TaskSpecification implements Specification<Task> {

    private final TaskFilterDTO taskFilter;
    private Class<?> typeField;

    public TaskSpecification(TaskFilterDTO taskFilter) {
        this.taskFilter = taskFilter;
    }

    @Override
    public Predicate toPredicate(Root<Task> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        if (!taskFilter.isHasNullValue()) {
            try {
                typeField = Task.class.getDeclaredField(taskFilter.getKey()).getType();
                if (typeField == String.class) {
                    return stringSpecification(root, query, criteriaBuilder);
                } else if (typeField.isEnum()) {
                    return enumSpecification(root, query, criteriaBuilder);
                }
                return null;
            } catch (NoSuchFieldException e) {
                throw new BadRequestException("Invalid key value");
            }
        } else {
            throw new BadRequestException("Body doesn't match a structure of a filter");
        }
    }

    private Predicate stringSpecification(Root<Task> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        switch (taskFilter.getOperation()) {
            case LIKE:
                return criteriaBuilder.like(root.get(taskFilter.getKey()), "%" + taskFilter.getValue() + "%");
            case EQUAL:
                return criteriaBuilder.equal(root.get(taskFilter.getKey()), taskFilter.getValue());
            case LESS_THAN:
                return criteriaBuilder.lessThan(root.get(taskFilter.getKey()), taskFilter.getValue());
            case GREATER_THAN:
                return criteriaBuilder.greaterThan(root.get(taskFilter.getKey()), taskFilter.getValue());
            default:
                throw new BadRequestException("Operation not supported yet");
        }
    }

    private Predicate enumSpecification(Root<Task> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        if (taskFilter.getOperation() == Operation.EQUAL) {
            return criteriaBuilder.equal(root.get(taskFilter.getKey()), castToRequiredType(typeField, taskFilter.getValue()));
        }
        throw new BadRequestException("Operation not supported yet");
    }

    private Object castToRequiredType(Class<?> fieldType, String value) {
        if (fieldType.isAssignableFrom(Status.class)) {
            return Status.valueOf(value);
        } else if (fieldType.isAssignableFrom(Type.class)) {
            return Type.valueOf(value);
        }
        return null;
    }

}
