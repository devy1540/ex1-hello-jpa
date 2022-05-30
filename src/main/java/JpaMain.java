import hello.jpa.*;
import hello.jpa.Address;
import hello.jpa.Member;
import hello.jpa.Movie;
import hello.jpa.Team;
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
            Address address = new Address("city", "street", "10000");

            Member member1 = new Member();
            member1.setUsername("member1");
            member1.setAddress(address);
            em.persist(member1);

            Address newAddress = new Address(address.getCity(), address.getStreet(), address.getZipcode());

//            em.flush();
//            em.clear();

//            Member refMember = em.getReference(Member.class, member1.getId());
//            System.out.println("refMember: " + refMember.getClass());
//            Hibernate.initialize(refMember);

//            System.out.println("isLoaded: " + emf.getPersistenceUnitUtil().isLoaded(refMember));
            /*Member m1 = em.find(Member.class, member1.getId());
            System.out.println("m1: " + m1.getClass());

            System.out.println("compare: " + (m1 == refMember));*/

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
