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
import metier.Emplacement;
import metier.Sejour;

@Path("/ressources")
public class WSRessource {

	/** Creates a new instance of WsSalutation */
	public WSRessource() {

	}

	@GET
	@Path("/getSejour/{id}")
	@Produces("application/json")
	// http://localhost:8080/ProjetRestFull/ressources/getjson
	public Sejour afficherSejour(@PathParam("id") String id) throws ParseException {
		HibernateClient hibernateClient = new HibernateClient();
		return hibernateClient.getUnSejour(Integer.parseInt(id));
	}

	@GET
	@Path("/getSejoursList")
	@Produces("application/json")
	// http://localhost:8080/ProjetRestFull/ressources/getjson
	public List<Sejour> afficherSejours() throws ParseException {
		HibernateClient hibernateClient = new HibernateClient();
		return hibernateClient.getTouslesSejours();
	}

	@GET
	@Path("/getSejoursOfClient/{idClient}")
	@Produces("application/json")
	public List<Sejour> afficherSejoursClient(@PathParam("idClient") String id) throws ParseException {
		HibernateClient hibernateClient = new HibernateClient();
		return hibernateClient.getSejoursOfClient(Integer.parseInt(id));
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

	@GET
	@Path("/getEmplacementsList")
	@Produces("application/json")
	// http://localhost:8080/ProjetRestFull/ressources/getjson
	public List<Emplacement> afficherEmplacements() throws ParseException {
		HibernateClient hibernateClient = new HibernateClient();
		return hibernateClient.getTouslesEmplacements();
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
			if (c != null) {
				HibernateClient hibernateClient = new HibernateClient();
				hibernateClient.saveClient(c);
			}
		} catch (IOException e) {
			e.printStackTrace();
			return e.getMessage();
		}
		return "Le client est enregistr�";
	}

	@POST
	@Path("/saveSejour")
	@Produces("application/json")
	@Consumes("application/json")
	public String saveSejour(String sejour) {
		ObjectMapper mapper = new ObjectMapper();
		Sejour s = null;
		try {
			s = mapper.readValue(sejour, Sejour.class);
			if (s != null) {
				HibernateClient hibernateClient = new HibernateClient();
				hibernateClient.saveSejour(s);
			}
		} catch (IOException e) {
			e.printStackTrace();
			return e.getMessage();
		}
		return "Le s�jour est enregistr�";
	}

	@POST
	@Path("/deleteClient")
	@Produces("application/json")
	@Consumes("application/json")
	public String deleteClient(String id) {
		HibernateClient hibernateClient = new HibernateClient();
		if (hibernateClient.deleteClient(id)) {
			return "Le client est supprim�.";
		} else {
			return "Impossible de supprimer le client.";
		}
	}

	@POST
	@Path("/deleteSejour")
	@Produces("application/json")
	@Consumes("application/json")
	public String deleteSejour(String id) {
		HibernateClient hibernateClient = new HibernateClient();
		if (hibernateClient.deleteSejour(id)) {
			return "Le s�jour est supprim�.";
		} else {
			return "Impossible de supprimer le s�jour.";
		}
	}

	@GET
	@Path("/hello/{unnom}")
	// r�cup�re la valeur pass�� par webResource.path("hello").path("xxxx")
	@Produces("text/plain")
	// http://localhost:8080/ProjetRestFull/ressources/hello/chistian
	public String donneBonjour(@PathParam("unnom") String name) {
		if (name != null) {
			return "Bonjour  " + name + " et bienvenue dans le monde RestFull!";
		}
		return "Bienvenue xxxxxx  dans le monde RestFul!";
	}

	//
	// Appel du param�tre avec la notation ?
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
	// Utilisation de la m�thode Post
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
	// r�cup�re la valeur pass�� par webResource.path("html").path("xxxx")
	@Produces("text/html")
	// http://localhost:8080/ProjRestFull/ressources/html/christian
	public String SalutationHtml(@PathParam("Id") String id) throws Exception {
		if (id != null) {
			return "<html><body><h1>Bonjour   " + id + " et bienvenue dans le monde RestFul! </body></h1></html>";
		}
		return "Bienvenue  id dans le monde RestFul!";
	}
}
