package service;

import org.springframework.data.domain.Pageable;

import bean.MembersReq;
import bean.MembersRes;
public interface IMemberService {

  
  public String create(MembersReq request);
  public void delete(String uuid);
  public void update(MembersReq request);
  public MembersRes findById(String id);
  public MembersRes findAll(Pageable pageable);

}
