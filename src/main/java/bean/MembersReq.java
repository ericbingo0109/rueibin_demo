package bean;

public class MembersReq {
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

  private String memberId;

	private String memberName;// 查詢條件:員工姓名

}
