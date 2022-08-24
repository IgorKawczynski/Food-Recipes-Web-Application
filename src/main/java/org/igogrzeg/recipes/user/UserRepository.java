package org.igogrzeg.recipes.user;

import org.igogrzeg.recipes.user.valueObjects.EmailValidator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findUserByEmail(EmailValidator emailValidator);

    UserEntity findUserEntityById(Long id);

}
