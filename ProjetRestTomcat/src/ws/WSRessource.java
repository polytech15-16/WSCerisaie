package ws;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.codehaus.jackson.map.ObjectMapper;

import dao.HibernateClient;
import metier.Client;

@Path("/ressources")
public class WSRessource {

	/** Creates a new instance of WsSalutation */
	public WSRessource() {
	}

	@GET
	@Path("/getClient/{id}")
	@Produces("application/json")
	// http://localhost:8080/ProjetRestFull/ressources/getjson
	public Client afficherClient(@PathParam("id") String id) throws ParseException {
		HibernateClient hibernateClient = new HibernateClient();
		return hibernateClient.getUnClient(Integer.parseInt(id));
	}

	@GET
	@Path("/getClientsList")
	@Produces("application/json")
	// http://localhost:8080/ProjetRestFull/ressources/getjson
	public List<Client> afficherClients() throws ParseException {
		HibernateClient hibernateClient = new HibernateClient();
		return hibernateClient.getTouslesClients();
	}

	@POST
	@Path("/saveClient")
	@Produces("application/json")
	@Consumes("application/json")
	public String saveClient(String client) {
		ObjectMapper mapper = new ObjectMapper();
		Client c = null;
		try {
			c = mapper.readValue(client, Client.class);
			System.out.println("Client : " + c.toString());
			if (c != null) {
				HibernateClient hibernateClient = new HibernateClient();
				hibernateClient.saveClient(c);
			}
		} catch (IOException e) {
			e.printStackTrace();
			return e.getMessage();
		}
		return "Le client est enregistré";
	}

	@POST
	@Path("/deleteClient")
	@Produces("application/json")
	@Consumes("application/json")
	public String deleteClient(String id) {
		HibernateClient hibernateClient = new HibernateClient();
		hibernateClient.deleteClient(id);
		return "Le client est enregistré";
	}

	@GET
	@Path("/hello/{unnom}")
	// récupère la valeur passéé par webResource.path("hello").path("xxxx")
	@Produces("text/plain")
	// http://localhost:8080/ProjetRestFull/ressources/hello/chistian
	public String donneBonjour(@PathParam("unnom") String name) {
		if (name != null) {
			return "Bonjour  " + name + " et bienvenue dans le monde RestFull!";
		}
		return "Bienvenue xxxxxx  dans le monde RestFul!";
	}

	//
	// Appel du paramètre avec la notation ?
	// /
	@GET
	@Produces("text/plain")
	// http://localhost:8080/ProjetWebRestFull/ressources?pnom=christian
	public String salutationParametree(@QueryParam("pnom") String pnom) {
		if (pnom != null) {
			return "Bonjour(QueryParam) " + pnom + " et bienvenue dans le monde Restful!";
		}
		return "Bienvenue pnom (queryparam)  dans le monde RestFul";
	}

	// /
	// Utilisation de la méthode Post
	// //
	@POST
	@Produces("text/plain")
	@Consumes("multipart/form-data")
	public String salutationMethodePost(String pnom) {
		if (pnom != null) {
			return "Bonjour pnompost (QueryParam) " + pnom + " et bienvenue dans le monde Restfull!";
		}
		return "Bienvenue pnompost (queryparam)  dans le monde RestFul";
	}

	//
	// Format de sortie : Html
	@GET
	@Path("html/{Id}")
	// récupère la valeur passéé par webResource.path("html").path("xxxx")
	@Produces("text/html")
	// http://localhost:8080/ProjRestFull/ressources/html/christian
	public String SalutationHtml(@PathParam("Id") String id) throws Exception {
		if (id != null) {
			return "<html><body><h1>Bonjour   " + id + " et bienvenue dans le monde RestFul! </body></h1></html>";
		}
		return "Bienvenue  id dans le monde RestFul!";
	}
}
