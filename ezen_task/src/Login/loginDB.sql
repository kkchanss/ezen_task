create table member (
    mid VARCHAR(20) NOT NULL primary key,
    mpwd varchar(20) not null
);
    
create table board (
	tid INT primary key,
    title varchar(20) not null,
    mid varchar(20),
    time  TIMESTAMP default current_timestamp,
    hits INT,
    foreign key(mid) references member(mid)
    );