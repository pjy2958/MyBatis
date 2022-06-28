package kr.ac.kopo.dao;

import kr.ac.kopo.Myconfig;
import kr.ac.kopo.vo.BoardVO;
import org.apache.ibatis.session.SqlSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class BoardDAO {
    private SqlSession session;
    public BoardDAO() {
        session = new Myconfig().getInstance();
    }
    public void insert() {
        BoardVO board = new BoardVO();
        board.setTitle("mybatis 삽입2");
        board.setWriter("user2");
        board.setContent("삽입완료2");

        session.insert("BoardDAO.insertBoard", board);
        session.commit();
        System.out.println("완료");
    }

    public void select() {
        List<BoardVO> boardList = session.selectList("BoardDAO.selectAllBoard");
        for(BoardVO board : boardList) {
            System.out.println(board);
        }
    }

    public void selectOne() {
        BoardVO vo = new BoardVO();
        vo.setNo(24);

        BoardVO board = session.selectOne("BoardDAO.selectAllBoard");
        System.out.println(board);
    }

    public void selectWhere() {
        List<BoardVO> list = session.selectList("BoardDAO.selectWhere", "우히");
        for(BoardVO vo : list) {
            System.out.println(vo);
        }
    }

    public void selectWhere2() {
        BoardVO vo = new BoardVO();

        // 1번 : 제목이 "mybatis 삽입" 이면서 작성자가 "user"인 게시물 조회
//        vo.setTitle("우히히");
//        vo.setWriter("홍길동");

        // 2번 : 제목이 "mybatis 삽입" 게시물 조회
//        vo.setTitle("우히히");
        // 3번 : 작성자가 "user"인 게시물 조회
        vo.setWriter("홍길동");


        List<BoardVO> list = session.selectList("BoardDAO.selectWhere2", vo);
        for(BoardVO board : list) {
            System.out.println(board);
        }
    }

    public void selectNos() {
        // 1, 2, 6, 10, 15, 24, 30, 33번 게시물 조회 => where no in (1, 2 ,6 ...)
        int[] nos = {1, 2, 6, 10, 15, 22, 23, 24};
        BoardVO vo = new BoardVO();
        vo.setNos(nos);

//        List<BoardVO> list = session.selectList("kr.ac.kopo.dao.BoardDAO.selectNos", vo);
        List<BoardVO> list = session.selectList("BoardDAO.selectNos2", nos);
        for(BoardVO board : list) {
            System.out.println(board);
        }
    }

    public void selectMap() {
        Map<String, String> map = new HashMap<>();
        map.put("title", "우히히");
        map.put("writer", "홍길동");

        List<BoardVO> list = session.selectList("BoardDAO.selectMap", map);
        for(BoardVO board : list) {
            System.out.println(board);
        }
    }

    public void selectMap2() {
        Map<String, Object> board = session.selectOne("BoardDAO.selectMap2", 24);

        Set<String> keys = board.keySet();
        for(String key : keys) {
            System.out.println(key + " : " + board.get(key));
        }
    }

    public void work() {
        insert();
//        select();
//        selectOne();
//        selectWhere();
//        selectWhere2();
//        selectNos();
//        selectMap();
//        selectMap2();
    }
}
