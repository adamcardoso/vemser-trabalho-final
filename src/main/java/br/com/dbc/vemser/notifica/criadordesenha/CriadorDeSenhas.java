package br.com.dbc.vemser.notifica.criadordesenha;

import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class CriadorDeSenhas {
    public static void main(String[] args) {
        Argon2PasswordEncoder argon2PasswordEncoder = new Argon2PasswordEncoder();
        String senha = argon2PasswordEncoder.encode("Senha123@");
        System.out.println(senha);


    }

}
