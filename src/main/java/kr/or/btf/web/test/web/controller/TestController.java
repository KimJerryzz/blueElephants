package kr.or.btf.web.test.web.controller;

import kr.or.btf.web.common.annotation.CurrentUser;
import kr.or.btf.web.common.aurora.AuroraAPIService;
import kr.or.btf.web.domain.web.Account;
import kr.or.btf.web.domain.web.MemberCrew;
import kr.or.btf.web.domain.web.MemberTeacher;
import kr.or.btf.web.repository.web.MemberCrewRepository;
import kr.or.btf.web.repository.web.MemberSchoolRepository;
import kr.or.btf.web.services.web.MemberService;
import kr.or.btf.web.services.web.MemberTeacherService;
import kr.or.btf.web.test.web.service.TestService;
import kr.or.btf.web.web.controller.BaseCont;
import kr.or.btf.web.common.aurora.AuroraForm;
import kr.or.btf.web.web.form.MemberForm;
import kr.or.btf.web.web.form.MemberSchoolForm;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/test")
public class TestController extends BaseCont {
    @Autowired
    TestService testService;

    private final AuroraAPIService auroraAPIService;
    private final MemberSchoolRepository memberSchoolRepository;
    private final MemberService memberService;
    private final MemberTeacherService memberTeacherService;
    private final MemberCrewRepository memberCrewRepository;


    @GetMapping(value = "/page")
    public String getMenuList(){
        return "/pages/blueElephant/testPage";
    }

    @GetMapping(value = "/namane")
    public String namaneTestPage(Model model){
        model.addAttribute("mc","memberJoin");
        return "/pages/blueElephant/namane";
    }

    @PostMapping(value = "/getQrImg")
    public String getQrImg(Model model, @ModelAttribute AuroraForm auroraForm) throws IOException {
        AuroraForm result = auroraAPIService.getBase64String(auroraForm);
        model.addAttribute("mc","memberJoin");
        model.addAttribute("aurora",result);
        return "/pages/blueElephant/namane_result";
    }
    // 엑셀 다운로드 시작
    @GetMapping(value = "/page/excel/download")
//    public String testExcel(Model model){
//        Pageable pageable = null;
//        model.addAttribute("noticeList", testService.getNewsListData(pageable));
//        return "/pages/blueElephant/testPage";
//    }
    public void excelDownload(HttpServletResponse response) throws IOException {
//        Workbook wb = new HSSFWorkbook();
        Workbook wb = new XSSFWorkbook();
        Sheet sheet = wb.createSheet("첫번째 시트");
        Row row = null;
        Cell cell = null;
        int rowNum = 0;

        // Header
        row = sheet.createRow(rowNum++);
        cell = row.createCell(0);
        cell.setCellValue("번호");
        cell = row.createCell(1);
        cell.setCellValue("이름");
        cell = row.createCell(2);
        cell.setCellValue("제목");

        // Body
        for (int i=0; i<3; i++) {
            row = sheet.createRow(rowNum++);
            cell = row.createCell(0);
            cell.setCellValue(i);
            cell = row.createCell(1);
            cell.setCellValue(i+"_name");
            cell = row.createCell(2);
            cell.setCellValue(i+"_title");
        }

        // 컨텐츠 타입과 파일명 지정
        response.setContentType("ms-vnd/excel");
//        response.setHeader("Content-Disposition", "attachment;filename=example.xls");
        response.setHeader("Content-Disposition", "attachment;filename=example.xlsx");

        // Excel File Output
        wb.write(response.getOutputStream());
        wb.close();
    }
    // 엑셀 다운로드 끝

    @RequestMapping("/pages/myPage/batchManagement")
    public String batchManagement(Model model,
                                  @CurrentUser Account account) {
        Account load = memberService.load(account.getId());
        MemberTeacher memberTeacher = memberTeacherService.loadByMber(load.getId());
        model.addAttribute("teacher", memberTeacher);
        model.addAttribute("mc", "myPage");



        return "/pages/myPage/batchManagement";
    }

    @RequestMapping(value = "/pages/myPage/batchManagement/batchRegister")
    public void batchRegister(MemberSchoolForm memberSchoolForm){
        memberService.batchRegister(memberSchoolForm);
    }

    @GetMapping(value = "/crewfinder")
    public ModelAndView crewfinder(MemberCrew memberCrew) {
        ModelAndView mav = new ModelAndView();
        //키워드 변수 선언 및 공백제거
        String keyword = memberCrew.getCrewNm();
        keyword.replace(" ", "");
        //키워드 로그
        System.out.println(" 공백이 제거 된 키워드 로그" + keyword);

        List<MemberCrew> memberCrewsList = memberCrewRepository.findByCrewNmContains(keyword);

        System.out.println("리스트에 담겨온 로그" + memberCrewsList);

        mav.addObject("Crewlist", memberCrewsList);
        mav.setViewName("/pages/member/register");

        return mav;
    }
}
