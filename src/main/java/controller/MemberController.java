package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

import bean.MembersReq;
import bean.MembersRes;
import service.IMemberService;

@RestController
@RequestMapping("/member")
public class MemberController {

  @Autowired
  private IMemberService memberService;
  
  @GetMapping()
  public MembersRes findById(MembersReq request, Pageable pageable) {
    
    return memberService.findById(request.getMemberId());
  }
  @GetMapping()
  public MembersRes findAll(MembersReq request, Pageable pageable) {
    
    return memberService.findAll(pageable);
  }
  @PutMapping()
  public void update(@RequestBody(required = true) MembersReq request){
    memberService.update(request);
  }
  @DeleteMapping(value = "/{uuid}")
  public void delete(@PathVariable(required = true, value = "uuid") String uuid, @RequestParam(required = true) String functionCode) throws JsonProcessingException {
    memberService.delete(uuid);
  }
  
}
