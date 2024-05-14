package com.example.EMTECH_ERP.BACKEND.AuthenticationModule.Users;
import com.example.EMTECH_ERP.BACKEND.AuthenticationModule.Roles.RoleRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class UsersService {
    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    private final Date today = new Date();

    public Users userRegistration(Users user){
        roleRepository.saveAll(user.getRoles());
        user.setCreatedOn(this.today);
        user.setDeleteFlag('N');
//        user.setModifiedOn(this.today);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return usersRepository.save(user);
    }

    public Users updateUser(Users user){
        return usersRepository.save(user);
    }

    public List<Users> getAllUsers(){
        return usersRepository.findAll();
    }

    public Users getUser(Long id) {
        return usersRepository.findById(id).orElse(null);
    }

    public List<Users> undeletedUsers(){
        return usersRepository.findByDeleteFlag('N');
    }

//    public EntityResponse lockUserAccount(String empNo) {
//        try {
//            EntityResponse response = new EntityResponse();
//            Optional<Users> userOptional = usersRepository.findByEmpNo(empNo);
//            if (userOptional.isPresent()) {
//                Users user = userOptional.get();
//                user.setAcctLocked(true);
//                user.setAcctActive(false);
//                user.setDeleteFlag('Y');
//                user.setDeletedOn(this.today);
//                Users updatedUser = usersRepository.save(user);
//                response.setMessage("User Account For Employee With employee number " + empNo + "Locked Successfully.");
//                response.setStatusCode(HttpStatus.OK.value());
//                response.setEntity(updatedUser);
//            } else {
//                response.setMessage(HttpStatus.NOT_FOUND.getReasonPhrase());
//                response.setStatusCode(HttpStatus.NOT_FOUND.value());
//                response.setEntity("");
//            }
//            return response;
//        } catch (Exception e) {
//            log.info("Error {} " + e);
//            return null;
//        }
//    }
}
