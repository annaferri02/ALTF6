package it.unife.ingsw202324.MicroservizioBase.services;


        import it.unife.ingsw202324.MicroservizioBase.models.Biglietto;
        import it.unife.ingsw202324.MicroservizioBase.models.Transazioni;
        import org.springframework.boot.autoconfigure.SpringBootApplication;
        import org.springframework.web.client.RestClient;


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
}