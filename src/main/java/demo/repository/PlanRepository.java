package demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import demo.model.Plan;

public interface PlanRepository extends JpaRepository<Plan, Long> {

}
