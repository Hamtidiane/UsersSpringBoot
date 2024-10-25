package edu.campus.numerique.client.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/*@RestController// Cette annotation permet de spécifier que cette classe représente un controlleur pour une API REST
public class HelloController {
    @GetMapping("/")//Permet d'associer une route pour une requête GET a la méthode contrôlleur, le contenu entre parenthèse peut être simplement la route ou bien des paramètres
    public String home() {
        return "<h2>Welcome home de la part de HamaDestroyer !</h2>";
    }

    @GetMapping(value={"/hello", "/hello/{name}"})
    public String hello(@PathVariable (required = false) String name) {
        if (name != null) {
            return "Hello " + name;
        }
        return "Go to hell, impolite one !";
    }
}*/
