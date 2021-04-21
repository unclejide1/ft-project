package com.fintech.demo.configuration;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fintech.demo.dao.AccountsDao;
import com.fintech.demo.dao.RoleDao;
import com.fintech.demo.model.Accounts;
import com.fintech.demo.model.Role;
import com.fintech.demo.model.enums.CardSchemes;
import com.fintech.demo.model.enums.CardType;
import com.fintech.demo.model.enums.ERole;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import io.micrometer.core.instrument.util.IOUtils;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

@Component
@FieldDefaults(makeFinal = true)
@Slf4j
public class Seed {


    private RoleDao roleDao;
    private AccountsDao accountsDao;
    private Gson gson;

    @Autowired
    public Seed(RoleDao roleDao, AccountsDao accountsDao, Gson gson) {

        this.roleDao = roleDao;
        this.accountsDao = accountsDao;
        this.gson = gson;
    }

    @PostConstruct
    public void Seeder(){
        Optional<Role> adminRole = roleDao.findByRole(ERole.ROLE_ADMIN);
        Optional<Role> userRole = roleDao.findByRole(ERole.ROLE_USER);

        if(!adminRole.isPresent()){
            createRole(ERole.ROLE_ADMIN);
        }
        if(!userRole.isPresent()){
            createRole(ERole.ROLE_USER);
        }

        long availableAccountsCount = accountsDao.countAccounts();
        if(availableAccountsCount == 0){
            createAccounts();
        }

    }

    public void  createAccounts(){
        InputStream inputStream = TypeReference.class.getResourceAsStream("/json/MOCK_DATA.json");
        try {
            String fileContent = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
            if(!StringUtils.isEmpty(fileContent)) {
                Type collectionType = new TypeToken<List<AccountsData>>(){}.getType();
                List<AccountsData> responseList = gson.fromJson(fileContent, collectionType);
                System.out.println("total tier levels: " + responseList.size());
                responseList.forEach(accountData -> {
                    Accounts account = Accounts.builder()
                            .accountNo(accountData.getAccountNo())
                            .cardSchemes(CardSchemes.valueOf(accountData.getCardScheme().toUpperCase()))
                            .cardType(CardType.valueOf(accountData.getCardType().toUpperCase()))
                            .bank(accountData.getBank())
                            .build();
                    accountsDao.saveRecord(account);
                });
            }
        }catch (Exception e){
            e.printStackTrace();
            log.info("Unable to create FAQ ");
        }

    }

    @Data
    private static class AccountsData {
        private String code;
        @SerializedName("accountNo")
        private String accountNo;
        @SerializedName("cardScheme")
        private String cardScheme;
        @SerializedName("cardType")
        private String cardType;
        @SerializedName("bank")
        private String bank;
    }


    public Role createRole(ERole role){
        return roleDao.saveRecord(Role.builder().name(role).build());

    }
}
