package edu.campus.numerique.client.controller;

import edu.campus.numerique.client.Client;
import edu.campus.numerique.client.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.*;

@RestController
@RequestMapping("api/clients")
public class ClientController {

    @Autowired
    private ClientRepository clientRepository; // Injection du repository

    private RestTemplate restTemplate = new RestTemplate(); // Définition de RestTemplate

    // Méthode pour afficher tous les clients
    @GetMapping
    public List<Client> getAllClients() {
        return clientRepository.findAll(); // Récupération depuis la base de données
    }

    // Méthode pour afficher un client par son ID
    @GetMapping("/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable Long id) {
        Optional<Client> client = clientRepository.findById(id); // Récupération depuis la base de données
        return client.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Méthode pour ajouter un nouveau client
    @PostMapping
    public ResponseEntity<String> addClient(@RequestBody Client newClient) {
        // Appel de l'API de vérification du numéro de permis
        String url = "http://localhost:8081/licenses/" + newClient.getNumeroPermis();
        ResponseEntity<Boolean> response = restTemplate.getForEntity(url, Boolean.class);

        if (response.getBody() != null && response.getBody()) {
            // Le permis est valide, on ajoute le client dans la base de données
            clientRepository.save(newClient);
            return ResponseEntity.status(HttpStatus.CREATED).body("Client ajouté avec succès");
        } else {
            // Le permis est invalide, on retourne une erreur
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Numéro de permis invalide");
        }
    }

    // Méthode pour mettre à jour un client existant
    @PutMapping("/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable Long id, @RequestBody Client updatedClient) {
        return clientRepository.findById(id).map(client -> {
            client.setNom(updatedClient.getNom());
            client.setPrenom(updatedClient.getPrenom());
            client.setDateNaissance(updatedClient.getDateNaissance());
            client.setNumeroPermis(updatedClient.getNumeroPermis());
            clientRepository.save(client); // Sauvegarde dans la base de données
            return ResponseEntity.ok(client);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Méthode pour supprimer un client par son ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        if (clientRepository.existsById(id)) {
            clientRepository.deleteById(id); // Suppression de la base de données
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
