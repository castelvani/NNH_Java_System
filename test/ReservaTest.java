/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Model.Reserva;
import java.text.DateFormat;
import java.text.ParseException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.text.SimpleDateFormat;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 *
 * @author Gabriel
 */
public class ReservaTest {
    
    public ReservaTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
     @Test
     public void calculaOrcamento() throws ParseException {
        Reserva reserva = new Reserva();
        String stringDt_entrada = "2020-01-01";
        String stringDt_saida = "2020-01-05";
        
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        Date Dtentrada = dateFormat.parse(stringDt_entrada);
        
        Date Dtsaida = dateFormat.parse(stringDt_saida);
        
        reserva.setDt_entrada((java.sql.Date) Dtentrada);
        reserva.setDt_saida((java.sql.Date) Dtsaida);
        reserva.getQuarto().setValorDiaria(10);
        
//        assertEquals(50.0, reserva.dias(dt_saida, dt_entrada));
    }
}
