package repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.fet.uewc.common.constants.UewcConstants;

import domain.Member;

/**
* @author Ray Wu
* @createDate 2020年12月8日
* Member維護
*/
public interface MemberRepo extends PagingAndSortingRepository<Member, String>{

	/**
	 * 取得Member 
	 * MemberType(0: 福委總網, 1: 活動組, 2: 社團組, 3: 福利組, 4: 跳蚤市場)
	 * */
	@Query("SELECT b FROM Member b JOIN FileMapping fm ON b.uuid = fm.srcUuid "
			+ " JOIN FileUpload fu ON fm.fileUploadUuid = fu.uuid "
			+ " WHERE b.MemberType = ?1 AND fm.fileType = '" + UewcConstants.FILE_TYPE_PICTURE + "'"
			+ " AND b.deleteFlag is null or b.deleteFlag <> 'Y' ORDER BY b.createDate ASC ")
	public Page<Member> findActiveMembers(String MemberType, Pageable pageable);
}
