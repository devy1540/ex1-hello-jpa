import hello.jpa.Member;
import hello.jpa.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        //persistence.xml에 있는 persistence-unit명
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        //데이터의 변경은 하나의 트랜잭션 안에서 실행할 수 있도록 해야한다.

        //JPQL은 엔티티 객체를 대상으로 쿼리 -> 객체지향SQL 이다...
        //SQL은 데이터베이스 테이블을 대상으로 쿼리
        try {
            //저장
            Team team = new Team();
            team.setName("분데스리가");
            em.persist(team);

            Member member = new Member();
            member.setUsername("member1");
            member.setTeam(team);

            em.persist(member);

            Member findMember = em.find(Member.class, member.getId());

            System.out.println("findTeam: " + findMember.getTeam().getName());


            Team team1 = em.find(Team.class, 4);
            findMember.setTeam(team1);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
