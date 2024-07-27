package it.unife.ingsw202324.MicroservizioBase.services;


        import it.unife.ingsw202324.MicroservizioBase.models.Biglietto;
        import it.unife.ingsw202324.MicroservizioBase.models.Evento;
        import it.unife.ingsw202324.MicroservizioBase.models.Luogo;
        import it.unife.ingsw202324.MicroservizioBase.models.Transazioni;
        import org.springframework.boot.autoconfigure.SpringBootApplication;
        import org.springframework.web.client.RestClient;

        import java.time.LocalDate;


@SpringBootApplication
public class TemplateRestConsumer {

    static String uriBaseMock = "http://localhost:3000/api/";

    public static String callREST(String resourceName, String uriBase, boolean useMock) {
        RestClient restClient = RestClient.create();
        /*
        Creo uriBase per chiamare Mockoon se l'impostazione è useMock
         */
        if(useMock)
            uriBase = uriBaseMock;

        System.out.println(uriBase+resourceName);

        return restClient.get()
                .uri(uriBase + resourceName)
                .retrieve()
                .body(String.class);
    }


    // Metodo per chiamare il servizio REST che ritorna un oggetto Biglietto
    public static Biglietto callREST2(String resourceName, String uriBase, boolean useMock) {
        RestClient restClient = RestClient.create();
        /*
        Creo uriBase per chiamare Mockoon se l'impostazione è useMock
         */
        if(useMock)
            uriBase = uriBaseMock;

        System.out.println(uriBase+resourceName);

        return restClient.get()
                .uri(uriBase + resourceName)
                .retrieve()
                .body(Biglietto.class);
    }

    public static Transazioni callREST3(String resourceName, String uriBase, boolean useMock) {
        RestClient restClient = RestClient.create();
        /*
        Creo uriBase per chiamare Mockoon se l'impostazione è useMock
         */
        if(useMock)
            uriBase = uriBaseMock;

        System.out.println(uriBase+resourceName);

        return restClient.get()
                .uri(uriBase + resourceName)
                .retrieve()
                .body(Transazioni.class);
    }

    public static Evento getEventoMock(String resourceName, String uriBase, boolean useMock) {
        /*RestClient restClient = RestClient.create();
        /*
        Creo uriBase per chiamare Mockoon se l'impostazione è useMock

        if(useMock)
            uriBase = uriBaseMock;

        System.out.println(uriBase+resourceName);
        String fullUri = uriBase + resourceName;


        try {
            return restClient.get()
                    .uri(fullUri)
                    .retrieve()
                    .body(Evento.class);
        } catch (Exception e) {
            System.err.println("Error while fetching Evento: " + e.getMessage());
            e.printStackTrace();
            return null;
        }*/

        Evento evento = new Evento();
        evento.setID_Evento("E0001");
        evento.setNome("Concerto Live");
        //evento.setData(20241231L);
        evento.setDescrizione("Concerto live di artisti famosi");
        evento.setID_Organizzatore("O0001");
        evento.setFlag_ticket(true);
        evento.setIdLuogo("L00001");
        return evento;
    }

    public static Luogo getLuogoMock(String resourceName, String uriBase, boolean useMock) {
     /*  RestClient restClient = RestClient.create();
        /*
        Creo uriBase per chiamare Mockoon se l'impostazione è useMock

        if(useMock)
            uriBase = uriBaseMock;

        System.out.println(uriBase+resourceName);
        String fullUri = uriBase + resourceName;


        try {
            return restClient.get()
                    .uri(fullUri)
                    .retrieve()
                    .body(Luogo.class);
        } catch (Exception e) {
            System.err.println("Error while fetching Evento: " + e.getMessage());
            e.printStackTrace();
            return null;
        }*/

        Luogo luogo = new Luogo();
        luogo.setIdLuogo("L00001");
        luogo.setNome("Teatro Nuovo");
        luogo.setVia("Via Roma");
        luogo.setNumCivico("1");
        luogo.setCap("00100");
        luogo.setProvincia("RM");
        luogo.setStato("Italia");
        luogo.setCitta("Roma");
        //luogo.setCapienza(500);
        luogo.setTipologia("Indoor");
        return luogo;
    }


}