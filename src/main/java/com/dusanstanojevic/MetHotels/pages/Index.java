/**
 * 
 */
package com.dusanstanojevic.MetHotels.pages;

import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;

import com.dusanstanojevic.MetHotels.dao.GenericDAO;
import com.dusanstanojevic.MetHotels.dao.KorisnikDAO;
import com.dusanstanojevic.MetHotels.entities.Korisnik;
import com.dusanstanojevic.MetHotels.util.Util;

/**
 * @author dusanstanojevic
 *
 */
public class Index {
	@Property
	@Persist
	private String username;
	@Property
	@Persist
	private String password;
	@Inject
	private GenericDAO<Korisnik> korisnikgDAO;
	@Inject
	private KorisnikDAO korisnikDAO;
	@SessionState
	private Korisnik k;
	
	@CommitAfter
	private Object onSuccess() {
		Korisnik k = korisnikDAO.find(username, Util.getMD5Hash(password));
		if (k != null)
			this.k = k;
		
		return Sobe.class;
	}
}