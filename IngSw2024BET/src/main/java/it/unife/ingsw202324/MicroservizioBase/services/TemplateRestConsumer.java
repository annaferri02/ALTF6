package it.unife.ingsw202324.MicroservizioBase.services;


        import com.fasterxml.jackson.databind.JsonNode;
        import com.fasterxml.jackson.databind.ObjectMapper;
        import it.unife.ingsw202324.MicroservizioBase.models.Biglietto;
        import it.unife.ingsw202324.MicroservizioBase.models.Evento;
        import it.unife.ingsw202324.MicroservizioBase.models.Luogo;
        import it.unife.ingsw202324.MicroservizioBase.models.Transazioni;
        import org.springframework.boot.autoconfigure.SpringBootApplication;
        import org.springframework.web.client.RestClient;

        import java.io.IOException;
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

        if(useMock)
            uriBase = uriBaseMock;

        System.out.println(uriBase+resourceName);

        return restClient.get()
                .uri(uriBase + resourceName)
                .retrieve()
                .body(Transazioni.class);
    }

    public static String getIdOrg(String resourceName, String uriBase, boolean useMock) {
        RestClient restClient = RestClient.create();
        if(useMock)
            uriBase = uriBaseMock;

        System.out.println(uriBase+resourceName);

        /*return restClient.get()
                .uri(uriBase + resourceName)
                .retrieve()
                .body(String.class);*/
        return "O0001";
    }

    public static Evento getEventoMock(String resourceName, String uriBase, boolean useMock) {
        RestClient restClient = RestClient.create();

        if (useMock) {
            uriBase = uriBaseMock;
        }

        String fullUri = uriBase + resourceName;
        System.out.println("Request URI: " + fullUri);

        try {
            // Recupera la risposta come una stringa JSON
            String jsonResponse = restClient.get()
                    .uri(fullUri)
                    .retrieve()
                    .body(String.class); // Usato block() per ottenere il risultato sincrono in un contesto non reattivo

            // Usa ObjectMapper per estrarre l'oggetto "evento"
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(jsonResponse);
            JsonNode eventoNode = rootNode.path("evento");

            // Deserializza solo l'oggetto "evento"
            Evento evento = objectMapper.treeToValue(eventoNode, Evento.class);

            System.out.println("Evento retrieved: " + evento);
            return evento;
        } catch (Exception e) {
            System.err.println("Error while fetching Evento: " + e.getMessage());
            e.printStackTrace();
            return null;
        }

    }

    public static Luogo getLuogoMock(String resourceName, String uriBase, boolean useMock) {
        RestClient restClient = RestClient.create();

        if (useMock) {
            uriBase = uriBaseMock;
        }

        String fullUri = uriBase + resourceName;
        System.out.println("Request URI: " + fullUri);

        try {
            // Recupera la risposta come una stringa JSON
            String jsonResponse = restClient.get()
                    .uri(fullUri)
                    .retrieve()
                    .body(String.class);

            // Usa ObjectMapper per estrarre l'oggetto "luogo"
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(jsonResponse);
            JsonNode luogoNode = rootNode.path("luogo");

            // Deserializza solo l'oggetto "luogo"
            Luogo luogo = objectMapper.treeToValue(luogoNode, Luogo.class);

            System.out.println("Luogo retrieved: " + luogo);
            return luogo;
        } catch (Exception e) {
            System.err.println("Error while fetching Luogo: " + e.getMessage());
            e.printStackTrace();
            return null;
        }

    }


}