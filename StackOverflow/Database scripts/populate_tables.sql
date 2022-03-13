INSERT INTO user_data(username, user_password)
VALUES ("Lungoci Luca", "luca123");

INSERT INTO user_data(username, user_password)
VALUES ("Carl Johnson", "San Andreas");

INSERT INTO user_data(username, user_password)
VALUES ("Tommy Vercetti", "Vice City");

INSERT INTO user_data(username, user_password)
VALUES ("Trevor Philips", "Los Santos");


INSERT INTO tags(tag_name)
VALUES ("sql");

INSERT INTO tags(tag_name)
VALUES ("java");

INSERT INTO tags(tag_name)
VALUES ("python");

INSERT INTO tags(tag_name)
VALUES ("oop");

INSERT INTO tags(tag_name)
VALUES ("error");

INSERT INTO tags(tag_name)
VALUES ("exception");

INSERT INTO tags(tag_name)
VALUES ("code");

INSERT INTO tags(tag_name)
VALUES ("try");




INSERT INTO entry(author_id, entry_text)
VALUES (2, 
"What are Null Pointer Exceptions (java.lang.NullPointerException) and what causes them?

What methods/tools can be used to determine the cause so that you stop the exception from causing the program to terminate prematurely?"
);

INSERT INTO question(entry_id, title)
VALUES(1, "NullPointerException");

INSERT INTO entry(author_id, entry_text)
VALUES (3, 
"How can I handle errors in Python with a try statement?"
);

INSERT INTO question(entry_id, title)
VALUES(2, "Error Handling");

INSERT INTO entry(author_id, entry_text)
VALUES (4, 
"NullPointerExceptions are exceptions that occur when you try to use a reference that points to no location in memory (null) as though it were referencing an object. 
Calling a method on a null reference or trying to access a field of a null reference will trigger a NullPointerException."
);

INSERT INTO entry(author_id, entry_text)
VALUES (4, 
"Why do we need null pointers in C? It seems that they only make the things harder..."
);

INSERT INTO entry(author_id, entry_text)
VALUES (3, "NullPointerExceptions thrown as a result of the method's logic, you can also check the method arguments for null values");

INSERT INTO question(entry_id, title)
VALUES(4, "Null Pointer C");

INSERT INTO answer(entry_id, question_answered_id)
VALUES (3, 1);

INSERT INTO answer(entry_id, question_answered_id)
VALUES (5, 1);


INSERT INTO question_tags(question_id, tag_id)
VALUES (1,2);

INSERT INTO question_tags(question_id, tag_id)
VALUES (1,5);

INSERT INTO question_tags(question_id, tag_id)
VALUES (2,3);

INSERT INTO question_tags(question_id, tag_id)
VALUES (2,5);

INSERT INTO question_tags(question_id, tag_id)
VALUES (2,8);


INSERT INTO votes(user_id, vote_type, entry_id)
VALUES (2, 1, 3);

INSERT INTO votes(user_id, vote_type, entry_id)
VALUES (1, 1, 3);

INSERT INTO votes(user_id, vote_type, entry_id)
VALUES (3, 1, 3);

INSERT INTO votes(user_id, vote_type, entry_id)
VALUES (1, -1, 5);
