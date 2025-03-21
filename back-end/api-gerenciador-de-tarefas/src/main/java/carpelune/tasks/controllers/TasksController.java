package carpelune.tasks.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import carpelune.tasks.dto.CreateTaskDTO;
import carpelune.tasks.dto.DeleteTaskDTO;
import carpelune.tasks.dto.UpdateTaskDTO;
import carpelune.tasks.models.Task;
import carpelune.tasks.services.TasksService;
import jakarta.validation.Valid;

@CrossOrigin
@RequestMapping("/tasks")
@RestController
public class TasksController {
	
	@Autowired
	private TasksService tasksServices;
	
	
	@PostMapping()
	public ResponseEntity<Task> createTask(@RequestBody @Valid CreateTaskDTO createTaskDTO){
		return this.tasksServices.createTask(createTaskDTO);
	}
	
	@GetMapping("/{taskId}")
	public ResponseEntity<Task> findTaskById(@PathVariable UUID taskId){
		return this.tasksServices.findTaskById(taskId);
	}
	
	@PutMapping()
	public ResponseEntity<Void> updateTask(@RequestBody @Valid UpdateTaskDTO updateTaskDTO){
		return this.tasksServices.updateTask(updateTaskDTO);
	}
	
	@DeleteMapping()
	public ResponseEntity<Void> deleteTask(@RequestBody @Valid DeleteTaskDTO deleteTaskDTO){
		return this.tasksServices.deleteTask(deleteTaskDTO);
	}
}
