package io.RcRenueva.client.api;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.RcRenueva.client.ApiClient;
import io.RcRenueva.client.api.ReporteDeCrditoRenuevaApi;
import io.RcRenueva.client.model.CatalogoEstados;
import io.RcRenueva.client.model.DomicilioPeticion;
import io.RcRenueva.client.model.PersonaPeticion;
import io.RcRenueva.client.model.Respuesta;
import okhttp3.OkHttpClient;

import org.junit.Assert;
import org.junit.Before;


import java.util.concurrent.TimeUnit;

public class ReporteDeCreditoRenuevaApiTest {
	
	private Logger logger = LoggerFactory.getLogger(ReporteDeCreditoRenuevaApiTest.class.getName());
	
    private final ReporteDeCrditoRenuevaApi api = new ReporteDeCrditoRenuevaApi();

    private ApiClient apiClient;
    private String xApiKey = "your_api_key";
    private String url = "the_url";
    
    @Before()
    public void setUp() {
    	 
		this.apiClient = api.getApiClient();
        this.apiClient.setBasePath(url);
        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
               .readTimeout(30, TimeUnit.SECONDS)
               .build();
        apiClient.setHttpClient(okHttpClient);
    }
    
    @Test
    public void getReporteTest() throws Exception {
        PersonaPeticion request = new PersonaPeticion();
        DomicilioPeticion domicilio = new DomicilioPeticion();
        
        domicilio.setDireccion("PASADISO ENCONTRADO 58");
        domicilio.setColoniaPoblacion("MONTEVIDEO");
        domicilio.setDelegacionMunicipio("GUSTAVO A MADERO");
        domicilio.setCiudad("CIUDAD DE MÉXICO");
        domicilio.setEstado(CatalogoEstados.CDMX);
        domicilio.setCP("07730");
        
        request.setApellidoPaterno("SESENTAYDOS");
        request.setApellidoMaterno("PRUEBA");
        request.setPrimerNombre("JUAN");
        request.setFechaNacimiento("1965-08-09");
        request.setRFC("SEPJ650809JG1");
        request.setNacionalidad("MX");
        request.setCuenta("CC7588788-A876");
        request.setDomicilio(domicilio);
        
        Respuesta response = api.getReporte(this.xApiKey, request);
        System.out.println(response.toString());
        logger.info("Report: "+response.toString());
        
        Assert.assertTrue(response.getFolioConsulta() != null);
    }
    
}
