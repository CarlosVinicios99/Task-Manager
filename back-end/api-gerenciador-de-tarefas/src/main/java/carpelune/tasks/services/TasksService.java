package carpelune.tasks.services;

import java.time.Instant;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import carpelune.tasks.dto.CreateTaskDTO;
import carpelune.tasks.models.Task;
import carpelune.tasks.repositories.TasksRepository;

@Service
public class TasksService {
	
	private Logger logger = Logger.getLogger(TasksService.class.getName());
	
	@Autowired
	private TasksRepository tasksRepository;
	
	
	public ResponseEntity<Task> createTask(CreateTaskDTO createTaskDTO){
		
		this.logger.log(Level.INFO, "creating a new task");
		
		try {
			Task newTask = new Task();
			
			newTask.setCreationTimestamp(Instant.now().toEpochMilli());
			newTask.setCreatorId(createTaskDTO.creatorId());
			newTask.setStatus(createTaskDTO.status());
			newTask.setDescription(createTaskDTO.description());
			newTask.setExpectedConclusionTimestamp(createTaskDTO.expectedConclusionTimestamp());
			newTask.setPriority(createTaskDTO.priority());
			newTask.setProjectId(createTaskDTO.projectId());
			newTask.setTitle(createTaskDTO.title());
			newTask.setWorkerId(createTaskDTO.workerId());
			
			this.logger.log(Level.WARNING, "saving new task in the database");
			newTask = this.tasksRepository.save(newTask);
			
			return ResponseEntity.status(HttpStatus.CREATED).body(newTask);
		}
		catch(Exception error) {
			this.logger.log(Level.SEVERE, "error while creating a task");
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
		}
	}
	
	public ResponseEntity<Task> findTaskById(UUID taskId){
		
	}

	
}
