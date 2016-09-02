package challenge.dao;

import javax.transaction.Transactional;

import challenge.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * A DAO for the entity User is simply created by extending the CrudRepository
 * interface provided by spring. The following methods are some of the ones
 * available from such interface: save, delete, deleteAll, findOne and findAll.
 * The magic is that such methods must not be implemented, and moreover it is
 * possible create new query methods working only by defining their signature!
 *
 * @author cyoun
 */
@Transactional
public interface UserDao extends CrudRepository<User, Long> {

    public User findByUsername(String username);

    public List<User> findAll();

    public User findOne(Long userId);

    public User save(User user);

    public void delete(User user);

}
