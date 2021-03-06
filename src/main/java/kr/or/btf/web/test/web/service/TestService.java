package kr.or.btf.web.test.web.service;

import kr.or.btf.web.common.Constants;
import kr.or.btf.web.common.exceptions.ValidCustomException;
import kr.or.btf.web.domain.web.Account;
import kr.or.btf.web.domain.web.BoardData;
import kr.or.btf.web.domain.web.MemberRoll;
import kr.or.btf.web.domain.web.MemberSchool;
import kr.or.btf.web.domain.web.enums.UserRollType;
import kr.or.btf.web.repository.web.MemberRepository;
import kr.or.btf.web.repository.web.MemberRollRepository;
import kr.or.btf.web.repository.web.MemberSchoolRepository;
import kr.or.btf.web.repository.web.MemberTeacherRepository;
import kr.or.btf.web.services.web.BoardDataService;
import kr.or.btf.web.services.web.MemberService;
import kr.or.btf.web.services.web._BaseService;
import kr.or.btf.web.web.form.BoardDataForm;
import kr.or.btf.web.web.form.MemberForm;
import kr.or.btf.web.web.form.MemberSchoolForm;
import kr.or.btf.web.web.form.SearchForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;

import javax.xml.bind.ValidationException;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TestService extends _BaseService {



    @Autowired
    BoardDataService boardDataService;

    private final MemberTeacherRepository teacherRepository;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final MemberService memberService;
    private final ModelMapper modelMapper;
    private final MemberSchoolRepository memberSchoolRepository;
    private final MemberRollRepository memberRollRepository;

    public  Page<BoardData> getNewsListData(@PageableDefault(page = 0, size = 3) Pageable pageable){
        BoardDataForm boardDataForm = new BoardDataForm();
        SearchForm searchForm = new SearchForm();
        searchForm.setPageSize(Constants.DEFAULT_PAGESIZE_5);
        boardDataForm.setMstPid(1L);
        Page<BoardData> noticeList = boardDataService.list(pageable, searchForm, boardDataForm);
        return noticeList;
    }


    /*@Transactional
    public void testBathRegister(MemberSchoolForm memberSchoolForm) {
        MemberForm memberForm = new MemberForm();
        String tempId = memberSchoolForm.getLoginId();
        String batch = "BATCH";

        for (int i = 1; i <= memberSchoolForm.getBatchArr(); i++) {
            //?????? ?????? ??????
            memberForm.setDelAt("N");
            memberForm.setPwd(passwordEncoder.encode(memberSchoolForm.getPwd())); //???????????? ???
            memberForm.setRegDtm(LocalDateTime.now()); //?????????
            memberForm.setPrtctorAttcAt("N");

            if(i<10){
                tempId = memberSchoolForm.getLoginId();
                tempId+="0"+i;
            } else {
                memberSchoolForm.getLoginId();
                tempId+=i;
            }
            memberForm.setLoginId(tempId);//????????? ?????? ???
            System.out.println(tempId);
            memberForm.setMberDvTy(UserRollType.BATCH);
            Account account = modelMapper.map(memberForm, Account.class);
            Account save = memberRepository.save(account);

            //?????????????????? ?????????
            tempId = "";

            MemberRoll memberRoll = new MemberRoll();
            memberRoll.setMberPid(save.getId());
            memberRoll.setMberDvTy(UserRollType.BATCH);
            memberRoll.setRegDtm(LocalDateTime.now());
            memberRoll.setRegPsId(save.getRegPsId());
            memberRollRepository.save(memberRoll);
            //memberSchool??? ????????? ????????? ???????????? ??????
            MemberSchool memberSchool = new MemberSchool();
            System.out.println(save.getId());
            memberSchoolRepository.pr_findTID( memberSchoolForm.getAreaNm() , memberSchoolForm.getSchlNm(),
                    memberSchoolForm.getGrade(), memberSchoolForm.getBan() , save.getId() ,LocalDateTime.now());
        }
    }*/
}
