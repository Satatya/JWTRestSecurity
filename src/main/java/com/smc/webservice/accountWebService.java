package com.smc.webservice;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.smc.dao.AccountDao;
import com.smc.model.UserModel;
import com.smc.utils.Constants;
import com.smc.utils.JWTYUtil;
import com.smc.utils.ResponseModel;

@Path("/account")
public class accountWebService {

	@GET
	@Path("test")
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	public ResponseModel test() {
		return new ResponseModel(200, "API is Working", Constants.SUCCESS, null, null, null);
	}
	
	@POST
	@Path("test")
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	public ResponseModel test1() {
		return new ResponseModel(200, "API is Working with POST", Constants.SUCCESS, null, null, null);
	}

	/**
	 * User Login
	 * 
	 * @param userModel
	 * @return
	 */
	@POST
	@Path("userlogin")
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	public ResponseModel userLogin(UserModel user) {
		user = AccountDao.userLogin(user.getUserId(), user.getUserName());
		if (user != null) {
			String token = new JWTYUtil().createJWT(user);
			List<UserModel> list = new ArrayList<UserModel>();
			list.add(user);
			if (token != null) {
				return new ResponseModel(161, "User Login Done", Constants.SUCCESS, list, token, null);
			} else {
				return new ResponseModel(161, "User Login Done, Unable to generate TOKEN", Constants.FAILURE, list, null, null);
			}
		}
		return new ResponseModel(161, "User Login Failed", Constants.FAILURE, null, null, null);
	}

	@POST
	@Path("verifytoken")
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	public ResponseModel userVerifiToken(@HeaderParam("token") String myToken) {
		if (myToken != null) {
			String userId = new JWTYUtil().parseJWT(myToken);
			if (userId != null && !userId.equals("0")) {
				return new ResponseModel(161, "Token Verified", Constants.SUCCESS, null, userId, null);
			} else {
				return new ResponseModel(161, "Token  Verification Failed", Constants.FAILURE, null, null, null);
			}
		}
		return new ResponseModel(161, "Token  Is Missing", Constants.FAILURE, null, null, null);

	}
}
