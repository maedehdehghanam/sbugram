package sbu.common;
import java.io.*;
import java.util.*;
public enum Command implements Serializable{
	CHECK_USERNAME,
	LOGIN,
	SIGNUP,
	LOGOUT,
	POST,
	LIKE,
	UNLIKE,
	EDIT_POST,
	DELETE_POST,
	COMMENT,
	UPDATE_PROFILE
}