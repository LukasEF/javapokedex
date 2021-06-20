package objs;

import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Pokemon {

    private String name;
    private String ability;
    private ArrayList<String> moves;
    private Image artwork = null;
    
    public Pokemon(){
        this.moves = new ArrayList<>();
        this.name = null;
        this.ability = null;
        this.artwork = null;
    }

    public Pokemon(String name, String ability, ArrayList<String> moves, String imageURL) {
        this.moves = new ArrayList<>();
        this.name = name;
        this.ability = ability;
        this.moves = moves;
        this.artwork = null;
        try {
            URL url = new URL(imageURL);
            artwork = ImageIO.read(url);
        } catch (IOException e) {

        }
    }

    public void createPokemonFromJSON(JSONObject pokeJSON) throws JSONException {
        this.name = pokeJSON.getJSONArray("forms").getJSONObject(0).getString("name");
        this.ability = pokeJSON.getJSONArray("abilities").getJSONObject(0).getJSONObject("ability").getString("name");
        JSONArray movesArray = pokeJSON.getJSONArray("moves");
        for (int i = 0; i < movesArray.length(); i++) {
            JSONObject move = movesArray.getJSONObject(i);
            this.moves.add(move.getJSONObject("move").getString("name"));
        }
        this.artwork = null;
        try {
            URL url = new URL(pokeJSON.getJSONObject("sprites").getJSONObject("other").getJSONObject("official-artwork").getString("front_default"));
            artwork = ImageIO.read(url);
        } catch (IOException e) {

        }
    }

    public String getName() {
        return name;
    }

    public String getAbility() {
        return ability;
    }

    public ArrayList<String> getMoves() {
        return moves;
    }

    public Image getImage() {
        return artwork;
    }
}
