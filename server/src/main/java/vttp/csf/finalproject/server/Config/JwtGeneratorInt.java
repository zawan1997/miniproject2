package vttp.csf.finalproject.server.Config;

import java.util.Map;

import vttp.csf.finalproject.server.Models.User;

public interface JwtGeneratorInt {
	Map<String, String> generateToken(User user);
}
