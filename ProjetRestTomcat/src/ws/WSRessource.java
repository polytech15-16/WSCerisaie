package ws;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import metier.Client;
import metier.Etudiant;

@Path("/ressources")
public class WSRessource {
	// @Context
	// private UriInfo context;

	/** Creates a new instance of WsSalutation */
	public WSRessource() {

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

	// /
	// On récupère un objet sous la forme XML
	// /
	@GET
	@Path("/get")
	@Produces("application/xml")
	// http://localhost:8080/ProjetWebRestFull/ressources/get
	public Etudiant getEtudiantToXML() throws ParseException {

		Etudiant unEtudiant = new Etudiant();
		unEtudiant.setNom("Vial");
		unEtudiant.setPrenom("Antoine");

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date unedate = sdf.parse("22/09/1988");
		unEtudiant.setDnaissance(unedate);
		return unEtudiant;
	}

	@GET
	@Path("/getjson")
	@Produces("application/json")
	// http://localhost:8080/ProjetRestFull/ressources/getjson
	public Etudiant getEtudiantToJSON() throws ParseException {

		Etudiant unEtudiant = new Etudiant();
		unEtudiant.setNom("Vial");
		unEtudiant.setPrenom("Antoine");

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date unedate = sdf.parse("22/09/1988");
		unEtudiant.setDnaissance(unedate);
		return unEtudiant;

	}

	@GET
	@Path("/getClientsList")
	@Produces("application/json")
	public List<Client> getClientsListToJSON() throws ParseException {
		// TODO faire l'appel a hibernate pouyr recuperer la liste des clients
		List<Client> listeClients = new ArrayList<Client>();
		for (int i = 0; i < 10; i++) {
			Client unClient = new Client();
			unClient.setNomCli("De Sousa" + i);
			unClient.setAdrRueCli("Rue anne frank" + i);
			unClient.setCpCli("38550" + i);
			unClient.setNumCli(1 + i);
			unClient.setVilleCli("Saint Maurice" + i);
			listeClients.add(unClient);
		}
		return listeClients;
	}

	@GET
	@Path("/getClient/{id}")
	@Produces("application/json")
	public Client getClientToJSON(@PathParam("id") String id) throws ParseException {
		// TODO faire l'appel a hibernate pouyr recuperer le client avec cet id

		Client unClient = new Client();
		unClient.setNomCli("De Sousa");
		unClient.setAdrRueCli("Rue anne frank");
		unClient.setCpCli("38550");
		unClient.setNumCli(Integer.parseInt(id));
		unClient.setVilleCli("Saint Maurice");

		return unClient;
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
