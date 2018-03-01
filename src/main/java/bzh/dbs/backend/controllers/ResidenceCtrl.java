package bzh.dbs.backend.controllers;

import bzh.dbs.backend.domain.Residence;
import bzh.dbs.backend.dao.ResidenceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Classe ResidenceCtrl.
 * Controleur de la classe Residence.
 * @author Jérémy Cavron
 * @version 1.0
 */
@Controller
public class ResidenceCtrl {

  // --- Déclaration des propriétés ---
  @Autowired
  private ResidenceDao residenceDao;

  /**
   * Fonction qui retourne toutes les residences.
   * @return residence : liste de residence.
   */
  @RequestMapping(
          value = "/allResid",
          method = RequestMethod.GET)
  @ResponseBody
  public List<Residence> getAll(){
    return this.residenceDao.getAll();
  }

  /**
   * Fcontion qui permet de créer une residence et retourne une réponse de confirmation.
   * @param taille : taille de la residence.
   * @param nbPieces : nombre de pièces de la residence.
   * @return String : message de confirmation.
   */
  @RequestMapping(
          value = "/createResid",
          method = RequestMethod.POST,
          params = {"taille", "nbPieces", "mail"})
  @ResponseBody
  public String create(double taille, int nbPieces) {
    try {
      Residence residence = new Residence(taille, nbPieces);
      residenceDao.create(residence);
    } catch (Exception exceptCreatPers) {
      return "controllers/ResidenceCtrl/create : Erreur de création de la residence : "
              + exceptCreatPers.toString();
    }
    return "Création de residence réussie !";
  }

  /**
   * Fonction qui permet de supprimer une residence et retourne un message de confirmation.
   * @param id : id de la residence à supprimer.
   * @return String : message de confirmation
   */
  @RequestMapping(
          value = "/deleteResid",
          method = RequestMethod.DELETE,
          params = {"id"})
  @ResponseBody
  public String delete(long id) {
    try {
      Residence residence = new Residence(id);
      residenceDao.delete(residence);
    } catch (Exception exceptDeletepers) {
      return "controllers/ResidenceCtrl/delete : Erreur de suppresion de la residence : "
              + exceptDeletepers.toString();
    }
    return "Suppression de residence réussie !";
  }

  /**
   * Fonction qui retourne la residence avec son id.
   * @param id : id de la residence
   * @return Residence : residence.
   */
  @RequestMapping(
          value = "/getResidById",
          method = RequestMethod.GET,
          params = {"id"})
  @ResponseBody
  public Residence getById(long id) {
    Residence residence;
    try {
      residence = residenceDao.getById(id);
    } catch (Exception exceptGetBMail) {
      return null;
    }
    return residence;
  }

  /**
   * Focntion qui met à jours la taille d'une residence dont l'id est passé
   * en paramètre.
   * @param id : id de la residence.
   * @param taille : nouvelle taille de la residence.
   * @return String : Message de confirmation.
   */
  @RequestMapping(
          value = "/updateTaille",
          method = RequestMethod.PUT,
          params = {"nom"})
  @ResponseBody
  public String updateName(long id, double taille) {
    try {
      Residence residence = residenceDao.getById(id);
      residence.setTaille(taille);
      residenceDao.update(residence);
    } catch (Exception exceptUpdName) {
      return "controllers/ResidenceCtrl/delete : Erreur de mise à jours du nom : "
              + exceptUpdName.toString();
    }
    return "Mise à jours du nom réussite !";
  }
}