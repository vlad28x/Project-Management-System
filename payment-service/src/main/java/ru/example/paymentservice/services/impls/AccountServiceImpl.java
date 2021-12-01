package ru.example.paymentservice.services.impls;

import org.mapstruct.factory.Mappers;
import org.springframework.core.NestedRuntimeException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.example.paymentservice.dto.AccountRequestDto;
import ru.example.paymentservice.dto.AccountResponseDto;
import ru.example.paymentservice.dto.DebtRequestDto;
import ru.example.paymentservice.dto.DebtResponseDto;
import ru.example.paymentservice.entities.Account;
import ru.example.paymentservice.exceptions.BadRequestException;
import ru.example.paymentservice.exceptions.NotFoundException;
import ru.example.paymentservice.repositories.AccountRepository;
import ru.example.paymentservice.services.AccountService;
import ru.example.paymentservice.utils.mappers.AccountMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {

    private static final AccountMapper accountMapper = Mappers.getMapper(AccountMapper.class);

    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public List<AccountResponseDto> getAll() {
        return accountRepository.findAll().stream()
                .map(accountMapper::accountToAccountResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public AccountResponseDto getById(Long id) {
        return accountMapper.accountToAccountResponseDto(accountRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Account with ID %s not found", id))));
    }

    @Override
    public AccountResponseDto add(AccountRequestDto newAccount) {
        try {
            return accountMapper.accountToAccountResponseDto(accountRepository.save(
                    accountMapper.accountRequestDtoToAccount(newAccount)
            ));
        } catch (NestedRuntimeException e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    @Override
    public AccountResponseDto update(AccountRequestDto newAccount) {
        try {
            return accountMapper.accountToAccountResponseDto(accountRepository.save(
                    accountMapper.accountRequestDtoToAccount(newAccount)
            ));
        } catch (NestedRuntimeException e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    @Override
    public void delete(Long id) {
        accountRepository.deleteById(id);
    }

    @Override
    public DebtResponseDto pay(DebtRequestDto currentDebt, String username) {
        /*Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = (String) authentication.getPrincipal();*/
        Account account = accountRepository.getAccountByUsername(username);
        Integer balance = account.getBalance();
        Integer debt = currentDebt.getDebt();
        DebtResponseDto responseDebt = new DebtResponseDto();
        if(debt <= balance) {
            account.setBalance(balance - debt);
            responseDebt.setDebt(0);
        } else {
            account.setBalance(0);
            responseDebt.setDebt(debt - balance);
        }
        accountRepository.save(account);
        return responseDebt;
    }


}
