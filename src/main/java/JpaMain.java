import hello.jpa.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
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

            Parent parent = new Parent();
            Child child1 = new Child();
            Child child2 = new Child();

            parent.addChild(child1);
            parent.addChild(child2);

            em.persist(parent);

            em.flush();
            em.clear();

            Parent p = em.find(Parent.class, parent.getId());

            p.getChildList().remove(0);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }

        emf.close();
    }

    private static void printMember(Member member) {
        System.out.println("name: " + member.getUsername());
    }

    private static void printMemberAndTeam(Member member) {
        String name = member.getUsername();
        System.out.println("username: " + name);

        Team team = member.getTeam();
        System.out.println("team: " + team.getName());
    }
}
