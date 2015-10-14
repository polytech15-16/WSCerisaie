package ws;

import javax.ws.rs.*;
import metier.Etudiant;
import java.util.*;
import java.text.*;

@Path("/ressources")
public class WSRessource {
	//@Context
	//private UriInfo context;

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

	//
	// Appel du paramètre avec la notation ?
	// /
	@GET
	@Produces("text/plain")
	// http://localhost:8080/ProjetWebRestFull/ressources?pnom=christian
	public String salutationParametree(@QueryParam("pnom") String pnom) {
		if (pnom != null) {
			return "Bonjour(QueryParam) " + pnom
					+ " et bienvenue dans le monde Restful!";
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
			return "Bonjour pnompost (QueryParam) " + pnom
					+ " et bienvenue dans le monde Restfull!";
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
			return "<html><body><h1>Bonjour   "
					+ id
					+ " et bienvenue dans le monde RestFul! </body></h1></html>";
		}
		return "Bienvenue  id dans le monde RestFul!";
	}
}
