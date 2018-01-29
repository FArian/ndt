package actors.serverInterface.json;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * Created by F.Arian on 06.11.17
 */
public class TOJSON {


    /**
     * Mapper
     */
    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static Object sendLocalHostAdress(String hostIP) {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.createObjectNode();
        ((ObjectNode) root).put("HostAddress", hostIP);
        return root;
    }

    public static Object replayMessage(String message) {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.createObjectNode();
        ((ObjectNode) root).put("Replay message", message);
        return root;
    }

    /**
     * Translates the request and information to connect a Android Client with the server
     *
     * @return
     */
    public static Object message(String key, String message) {
        JsonNode msg = MAPPER.createObjectNode();
        JsonNode root = MAPPER.createObjectNode();
        ((ObjectNode) msg).put("Message", message);
        ((ObjectNode) root).put(key, msg);
        return root;
    }

    /**
     * boolean answer
     *
     * @return
     */
    public static Object booleanAnswer(String key, boolean answer) {
        JsonNode msg = MAPPER.createObjectNode();
        JsonNode root = MAPPER.createObjectNode();
        ((ObjectNode) msg).put("Message", answer);
        ((ObjectNode) root).put(key, msg);
        return root;
    }

    /**
     * @param key
     * @param object
     * @return String Json Message
     */
    public static Object sendObjectWithKey(String key, Object object) {
        JsonNode root = MAPPER.createObjectNode();
        JsonNode node = MAPPER.convertValue(object.toString(), JsonNode.class);
        ((ObjectNode) root).put(key, node);
        return root;
    }


}
