package model;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Iterables;

@Service
@Transactional
public class UserService {

	@PersistenceContext(unitName = "localEntityManagerUnit")
	protected EntityManager em;
	
	public User add(User user) {
		user.setCreated_at(new Date());
		user.setUpdated_at(new Date());
		em.persist(user);
		return user;
	}
	
	public User add(String email, String pw) {
		User user = new User();
		user.setEmail(email);
		user.setPw(pw);
		user.setCreated_at(new Date());
		user.setUpdated_at(new Date());
		em.persist(user);
		return user;
	}
	
	public User get(long id) {
		return em.find(User.class, id);
	}
	
	public List<User> gets() {
		return em.createNativeQuery("SELECT * FROM user", User.class).getResultList();
	}
	
	public User get(String email) {
		System.out.println("email:"+email);
		String sql = "SELECT * FROM user WHERE email like :email";
		List<User> users = em.createNativeQuery(sql, User.class).setParameter("email", email).getResultList();
		return Iterables.getOnlyElement(users, null);
	}
}
