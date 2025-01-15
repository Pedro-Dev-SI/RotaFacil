package com.api.rota_facil.services;

import com.api.rota_facil.domains.Rota;
import com.api.rota_facil.repositories.RotaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class RotaService {

    private RotaRepository rotaRepository;

    public RotaService(RotaRepository rotaRepository) {
        this.rotaRepository = rotaRepository;
    }

    public String generateCodigoRota() {
        // Obter o mês e o ano atuais
        LocalDate currentDate = LocalDate.now();
        int mes = currentDate.getMonthValue();
        int ano = currentDate.getYear();

        // Consultar no banco de dados o número de rotas criadas nesse mês e ano
        long quantidadeDeRotas = rotaRepository.countRotasPorMesEAno(mes, ano);

        // Formatar o código: ROT + MÊS + ANO + CONTADOR
        String mesFormatado = String.format("%02d", mes);  // Formatar mês com 2 dígitos
        String anoFormatado = String.format("%04d", ano);  // Formatar ano com 4 dígitos
        String contadorFormatado = String.format("%04d", (quantidadeDeRotas + 1));  // O contador é a quantidade de rotas já existentes + 1

        return "ROT" + mesFormatado + anoFormatado + contadorFormatado;
    }

    public Rota createRota() {
        var codRota = generateCodigoRota();

        Rota novaRota = new Rota();
        novaRota.setCodigo(codRota);

        Rota rotaSalva = rotaRepository.save(novaRota);

        return rotaSalva;

    };
}
