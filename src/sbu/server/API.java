package sbu.server;

import sbu.common.*;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;
public class API{
	public static Map<String,Object> doesUserNameExists(Map<String,Object> income){

		String check = (String) income.get("username");
		Profile profile = Server.profiles.get(check);
		Boolean exists = (profile != null);

		Map<String,Object> ans = new HashMap<>();
		ans.put("answer",exists);
		ans.put("command",Command.CHECK_USERNAME);

		return ans;
	}
}