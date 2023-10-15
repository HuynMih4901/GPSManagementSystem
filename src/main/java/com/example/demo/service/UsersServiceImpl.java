package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.UsersDto;
import com.example.demo.exceptions.LoginException;
import com.example.demo.exceptions.UsersNotFoundException;
import com.example.demo.model.Device;
import com.example.demo.model.CurrentUserSession;
import com.example.demo.model.Users;
import com.example.demo.repository.DeviceRepository;
import com.example.demo.repository.CurrentAdminSessionDao;
import com.example.demo.repository.CurrentUserSessionDao;
import com.example.demo.repository.UsersRepository;


@Service
public class UsersServiceImpl implements UsersService {

	@Autowired
	private UsersRepository usersRepository;
	@Autowired
	private DeviceRepository deviceRepository;
	@Autowired
	private CurrentUserSessionDao currentUserSessionDao;

	
	
@Override
public Users addNewUsers(Users users) throws UsersNotFoundException {
		
		Users existingUser = usersRepository.findByMobileNumber(users.getMobileNumber());
	    if (existingUser == null) {
	    	Device device = new Device();
	    	users.setCart(device);
	    	device.setUsers(users);
	    	return usersRepository.save(users);
	   
	    }else {
	    
	        throw new UsersNotFoundException("Users with this mobile number already exists...");
	    }
	    
	}



@Override
public UsersDto updateUser(Long userId, UsersDto usersDto) throws UsersNotFoundException, LoginException {
	
	Optional<Users> isUserPresent=usersRepository.findById(userId);
	if(isUserPresent.isPresent()) {
		Users users=isUserPresent.get();
		Optional<CurrentUserSession> isCurrent=currentUserSessionDao.findById(users.getUserId());
		if(isCurrent.isPresent()) {
		    users.getAddress().setCountry(usersDto.getCountry());
		    users.getAddress().setProvince(usersDto.getProvince());
		    users.getAddress().setDistrict(usersDto.getDistrict());
		    users.getAddress().setWard(usersDto.getWard());
		    users.setEmail(usersDto.getEmail());
		    users.setName(usersDto.getName());
		    users.setMobileNumber(usersDto.getMobileNumber());
		    users.setPassword(usersDto.getPassword());
		    users.setUsername(usersDto.getUsername());

		    usersRepository.save(users);
		    return usersDto;
		}
		else {
			throw new LoginException("User is not logged in..");
		}
	}else {
		throw new UsersNotFoundException("User is not found with this id-"+userId);
	}
}



@Override
public String deleteUser(Long userId) throws UsersNotFoundException, LoginException {
	Optional<Users> isUserPresent = usersRepository.findById(userId);
    if (isUserPresent.isPresent()) {
        Users user = isUserPresent.get();
        Optional<CurrentUserSession> isCurrent = currentUserSessionDao.findById(user.getUserId());
        if (isCurrent.isPresent()) {
            CurrentUserSession currentUserSession = isCurrent.get();
            if (currentUserSession != null) {
                currentUserSessionDao.delete(currentUserSession);
            }
            Device device = user.getCart();
            if (device != null) {
                deviceRepository.delete(device);
            }
            usersRepository.delete(user);
        } else {
            throw new LoginException("user is not logged in");
        }
        return "User is deleted successfully";
    } else {
        throw new UsersNotFoundException("No user with this userId-" + userId);
    }
}
	

	
	
	
}
	
	
	

