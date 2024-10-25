package edu.campus.numerique.client.controller;

import edu.campus.numerique.client.Client;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;

@RestController
@RequestMapping("/clients")
public class ClientController {

    // Liste de clients définie en dur
    private static List<Client> clients = new ArrayList<>();//Arrays.asList(


    static {
        clients.add(new Client(1, "Doe", "John", LocalDate.of(1990, 1, 1), "ABC123456"));
        clients.add(new Client(2, "Smith", "Jane", LocalDate.of(1985, 5, 15), "XYZ987654"));
        clients.add(new Client(3, "Brown", "Alice", LocalDate.of(2000, 12, 10), "LMN456789"));
    }

    // Méthode pour afficher tous les clients
    @GetMapping
    public List<Client> getAllClients() {
        System.out.println(clients);
        return clients;
    }

    // Méthode pour afficher un client par son ID
  @GetMapping("/{id}")
  public Client getClientById(@PathVariable Long id) {
      Optional<Client> client = clients.stream()
              .filter(c -> c.getId() == (id))
              .findFirst();
      return client.orElse(null); // Retourne null si le client n'est pas trouvé
  }

    // Méthode pour ajouter un nouveau client
    @PostMapping
    public ResponseEntity<Client> addClient(@RequestBody Client newClient) {
        newClient.setId(clients.size() + 1); // Génère un nouvel ID
        clients.add(newClient);
        return ResponseEntity.status(HttpStatus.CREATED).body(newClient);
    }

    // Méthode pour mettre à jour un client existant
    @PutMapping("/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable Long id, @RequestBody Client updatedClient) {
        Optional<Client> clientOptional = clients.stream()
                .filter(c -> c.getId() == (id))
                .findFirst();

        if (clientOptional.isPresent()) {
            Client existingClient = clientOptional.get();
            existingClient.setNom(updatedClient.getNom());
            existingClient.setPrenom(updatedClient.getPrenom());
            existingClient.setDateNaissance(updatedClient.getDateNaissance());
            existingClient.setNumeroPermis(updatedClient.getNumeroPermis());
            return ResponseEntity.ok(existingClient);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Méthode pour supprimer un client par son ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        boolean removed = clients.removeIf(client -> client.getId() == (id));
        if (removed) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
