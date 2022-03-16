package bean;

public class MembersUnit {

  public String getMemberId() {
    return memberId;
  }
  public void setMemberId(String memberId) {
    this.memberId = memberId;
  }
  public String getMemberName() {
    return memberName;
  }
  public void setMemberName(String memberName) {
    this.memberName = memberName;
  }
  public String getUuid() {
    return uuid;
  }
  private String uuid;
  private String memberId;
  private String memberName;// 查詢條件:員工姓名
}
