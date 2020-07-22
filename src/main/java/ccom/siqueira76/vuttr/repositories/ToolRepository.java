package ccom.siqueira76.vuttr.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ccom.siqueira76.vuttr.domain.Tool;

public interface ToolRepository extends JpaRepository<Tool, Integer> {
	
	public List<Tool> findToolsByTags(String  nome);
	
}
