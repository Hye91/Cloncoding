package hello.servlet.web.servlet;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "memberListServlet", urlPatterns = "/servlet/members")
public class MemberListServlet extends HttpServlet {

    private MemberRepository memberRepository = MemberRepository.getInstance();
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //저장한 회원의 리스트를 가져오는것
        List<Member> members = memberRepository.findAll(); //ctrl + alt + v : 변수 바로 꺼내오기

        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");

        //값 가져온걸 동적으로 뿌리면 된다. -> for문 사용
        PrintWriter w = response.getWriter();
        w.write("<html>");
        w.write("<head>");
        w.write(" <meta charset=\"UTF-8\">");
        w.write(" <title>Title</title>");
        w.write("</head>");
        w.write("<body>");
        w.write("<a href=\"/index.html\">메인</a>");
        w.write("<table>"); //표모양으로 만드는 태그
        w.write(" <thead>");
        w.write(" <th>id</th>");
        w.write(" <th>username</th>");
        w.write(" <th>age</th>");
        w.write(" </thead>");
        w.write(" <tbody>");

//         w.write(" <tr>");
//         w.write(" <td>1</td>");
//         w.write(" <td>userA</td>");
//         w.write(" <td>10</td>");
//         w.write(" </tr>"); //이 구문은 정적인 데이터 userA, 10 이 값이 뿌려지게 된다.

        for (Member member : members) { //저장된 list를 반복문 돌리면서 뿌린다.
            w.write(" <tr>");
            w.write(" <td>" + member.getId() + "</td>");
            w.write(" <td>" + member.getUsername() + "</td>");
            w.write(" <td>" + member.getAge() + "</td>");
            w.write(" </tr>");
        }
        w.write(" </tbody>");
        w.write("</table>");
        w.write("</body>");
        w.write("</html>");
    }
}
