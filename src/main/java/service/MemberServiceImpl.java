package service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.fet.uewc.common.bean.memberUnit;
import com.fet.uewc.common.constants.UewcConstants;
import com.fet.uewc.common.constants.message.ErrorMessage;
import com.fet.uewc.common.exception.BadRequestException;
import com.fet.uewc.repo.main.domain.member;

import bean.MembersReq;
import bean.MembersRes;
import bean.RestResponsePage;
import repository.MemberRepo;

public class MemberServiceImpl implements IMemberService{

  @Autowired
  private MemberRepo memberRepo;
  @Override
  public String create(MembersReq request) {
    if (!StringUtils.isEmpty(request.getUuid())) {
      Optional<member> omember = memberRepo.findById(request.getUuid());
      if (omember.isPresent()) {
        throw new BadRequestException(ErrorMessage.NEW_ITEM_FAIL);
      }
    }
    member member = new member();
    if (isValid(request)) {
      BeanUtils.copyProperties(request, member);
      memberRepo.save(member);
    }
    return memberUuid;
  }

  @Override
  public void delete(String uuid) {
    if (StringUtils.isEmpty(uuid)) {
      throw new BadRequestException(ErrorMessage.QUERY_NO_RESULT);
    }
    Optional<member> omember = memberRepo.findById(uuid);
    if (!omember.isPresent()) {
      throw new BadRequestException(ErrorMessage.QUERY_NO_RESULT);
    }
    member deleteTarget = omember.get();
    deleteTarget.setDeleteUser(auditor.getCurrentAuditor().get());
    deleteTarget.setDeleteDate(LocalDateTime.now());
    deleteTarget.setDeleteFlag(UewcConstants.DELETE_FLAG_Y);
    memberRepo.save(deleteTarget);
  }

  @Override
  public void update(MembersReq request) {
    Optional<member> omember = memberRepo.findById(request.getUuid());
    if (!omember.isPresent()) {
      throw new BadRequestException(ErrorMessage.UPDATE_FAIL);
    }
    
  }

  @Override
  public MembersRes findById(String id) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public MembersRes findAll(Pageable pageable) {
    Page<member> members = memberRepo.findActivemembers(memberType, pageable);
    RestResponsePage<memberUnit> memberUnits = new RestResponsePage<>(tempList, members.getPageable(),
        members.getTotalElements());
    return memberRes.builder().memberUnits(memberUnits).build();
  }

}
