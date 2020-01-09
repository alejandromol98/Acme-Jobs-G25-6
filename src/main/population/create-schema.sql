
    create table `administrator` (
       `id` integer not null,
        `version` integer not null,
        `user_account_id` integer,
        primary key (`id`)
    ) engine=InnoDB;

    create table `announcement` (
       `id` integer not null,
        `version` integer not null,
        `moment` datetime(6),
        `more_info` varchar(255),
        `text` varchar(255),
        `title` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `anonymous` (
       `id` integer not null,
        `version` integer not null,
        `user_account_id` integer,
        primary key (`id`)
    ) engine=InnoDB;

    create table `application` (
       `id` integer not null,
        `version` integer not null,
        `justification` varchar(255),
        `moment` datetime(6),
        `qualifications` varchar(1024),
        `reference` varchar(255),
        `skills` varchar(1024),
        `statement` varchar(255),
        `status` integer,
        `job_id` integer not null,
        `worker_id` integer not null,
        primary key (`id`)
    ) engine=InnoDB;

    create table `audit_record` (
       `id` integer not null,
        `version` integer not null,
        `body` varchar(255),
        `moment` datetime(6),
        `status` integer,
        `title` varchar(255),
        `auditor_id` integer not null,
        `job_id` integer not null,
        primary key (`id`)
    ) engine=InnoDB;

    create table `auditor` (
       `id` integer not null,
        `version` integer not null,
        `user_account_id` integer,
        `firm` varchar(255),
        `responsibility_statement` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `authenticated` (
       `id` integer not null,
        `version` integer not null,
        `user_account_id` integer,
        primary key (`id`)
    ) engine=InnoDB;

    create table `challenge` (
       `id` integer not null,
        `version` integer not null,
        `deadline` datetime(6),
        `description` varchar(1024),
        `reward_goal_bronze_amount` double precision,
        `reward_goal_bronze_currency` varchar(255),
        `reward_goal_gold_amount` double precision,
        `reward_goal_gold_currency` varchar(255),
        `reward_goal_silver_amount` double precision,
        `reward_goal_silver_currency` varchar(255),
        `title` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `company_record` (
       `id` integer not null,
        `version` integer not null,
        `ceo` varchar(255),
        `description` varchar(1024),
        `email` varchar(255),
        `incorporated` bit not null,
        `name` varchar(255),
        `sector` varchar(255),
        `telephone` varchar(255),
        `website` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `consumer` (
       `id` integer not null,
        `version` integer not null,
        `user_account_id` integer,
        `company` varchar(255),
        `sector` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `curriculum` (
       `id` integer not null,
        `version` integer not null,
        `moment` datetime(6),
        `name` varchar(255),
        `skills` varchar(255),
        `studies` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `customisation` (
       `id` integer not null,
        `version` integer not null,
        `customisations` varchar(1024),
        `threshold` integer,
        primary key (`id`)
    ) engine=InnoDB;

    create table `duty` (
       `id` integer not null,
        `version` integer not null,
        `description` varchar(255),
        `percentage` integer,
        `title` varchar(255),
        `job_id` integer not null,
        primary key (`id`)
    ) engine=InnoDB;

    create table `employer` (
       `id` integer not null,
        `version` integer not null,
        `user_account_id` integer,
        `company` varchar(255),
        `sector` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `investor` (
       `id` integer not null,
        `version` integer not null,
        `name` varchar(255),
        `sector` varchar(255),
        `stars` integer,
        `statement` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `job` (
       `id` integer not null,
        `version` integer not null,
        `deadline` datetime(6),
        `description` varchar(1024),

        `final_mode` bit not null,
        `more_info` varchar(255),
        `reference_number` varchar(255),
        `salary_amount` double precision,
        `salary_currency` varchar(255),
        `title` varchar(255),
        `employer_id` integer not null,
        primary key (`id`)
    ) engine=InnoDB;

    create table `message` (
       `id` integer not null,
        `version` integer not null,
        `body` varchar(255),
        `moment` datetime(6),
        `tags` varchar(255),
        `title` varchar(255),
        `authenticated_id` integer not null,
        `message_thread_id` integer not null,
        primary key (`id`)
    ) engine=InnoDB;

    create table `message_thread` (
       `id` integer not null,
        `version` integer not null,
        `moment` datetime(6),
        `title` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;


    create table `offer` (
       `id` integer not null,
        `version` integer not null,
        `deadline` datetime(6),
        `max_amount` double precision,
        `max_currency` varchar(255),
        `min_amount` double precision,
        `min_currency` varchar(255),
        `moment` datetime(6),
        `text` varchar(1024),
        `ticker` varchar(255),
        `title` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;


    create table `person` (
       `id` integer not null,
        `version` integer not null,
        `author` bit not null,
        `authenticated_id` integer not null,
        `message_thread_id` integer not null,
        primary key (`id`)
    ) engine=InnoDB;

    create table `provider` (
       `id` integer not null,
        `version` integer not null,
        `user_account_id` integer,
        `company` varchar(255),
        `sector` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `request` (
       `id` integer not null,
        `version` integer not null,
        `deadline` datetime(6),
        `description` varchar(1024),
        `moment` datetime(6),
        `reward_amount` double precision,
        `reward_currency` varchar(255),
        `stars` integer,
        `telephone` varchar(255),
        `ticker` varchar(255),
        `title` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `shout` (
       `id` integer not null,
        `version` integer not null,
        `author` varchar(255),
        `moment` datetime(6),
        `text` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `storage` (
       `id` integer not null,
        `version` integer not null,
        `user_account_id` integer,
        `firm` varchar(255),
        `responsibility_statement` varchar(255),
        `status` integer,
        `authenticated_id` integer not null,
        primary key (`id`)
    ) engine=InnoDB;


    create table `user_account` (
       `id` integer not null,
        `version` integer not null,
        `enabled` bit not null,
        `identity_email` varchar(255),
        `identity_name` varchar(255),
        `identity_surname` varchar(255),
        `password` varchar(255),
        `username` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `worker` (
       `id` integer not null,
        `version` integer not null,
        `user_account_id` integer,
        `qualifications_record` varchar(255),
        `skills_record` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `hibernate_sequence` (
       `next_val` bigint
    ) engine=InnoDB;

    insert into `hibernate_sequence` values ( 1 );

    alter table `application` 
       add constraint UK_ct7r18vvxl5g4c4k7aefpa4do unique (`reference`);

    alter table `job` 
       add constraint UK_bos0omdc9s5vykasqjhwaq65m unique (`reference_number`);

    alter table `offer` 
       add constraint UK_iex7e8fs0fh89yxpcnm1orjkm unique (`ticker`);

    alter table `request` 
       add constraint UK_9mxq3powq8tqctclj0fbi2nih unique (`ticker`);

    alter table `user_account` 
       add constraint UK_castjbvpeeus0r8lbpehiu0e4 unique (`username`);

    alter table `administrator` 
       add constraint FK_2a5vcjo3stlfcwadosjfq49l1 
       foreign key (`user_account_id`) 
       references `user_account` (`id`);

    alter table `anonymous` 
       add constraint FK_6lnbc6fo3om54vugoh8icg78m 
       foreign key (`user_account_id`) 
       references `user_account` (`id`);

    alter table `application` 
       add constraint `FKoa6p4s2oyy7tf80xwc4r04vh6` 
       foreign key (`job_id`) 
       references `job` (`id`);

    alter table `application` 
       add constraint `FKmbjdoxi3o93agxosoate4sxbt` 
       foreign key (`worker_id`) 
       references `worker` (`id`);

    alter table `audit_record` 
       add constraint `FKdcrrgv6rkfw2ruvdja56un4ji` 
       foreign key (`auditor_id`) 
       references `auditor` (`id`);

    alter table `audit_record` 
       add constraint `FKlbvbyimxf6pxvbhkdd4vfhlnd` 
       foreign key (`job_id`) 
       references `job` (`id`);

    alter table `auditor` 
       add constraint FK_clqcq9lyspxdxcp6o4f3vkelj 
       foreign key (`user_account_id`) 
       references `user_account` (`id`);

    alter table `authenticated` 
       add constraint FK_h52w0f3wjoi68b63wv9vwon57 
       foreign key (`user_account_id`) 
       references `user_account` (`id`);

    alter table `consumer` 
       add constraint FK_6cyha9f1wpj0dpbxrrjddrqed 
       foreign key (`user_account_id`) 
       references `user_account` (`id`);

    alter table `duty` 
       add constraint `FKs2uoxh4i5ya8ptyefae60iao1` 
       foreign key (`job_id`) 
       references `job` (`id`);

    alter table `employer` 
       add constraint FK_na4dfobmeuxkwf6p75abmb2tr 
       foreign key (`user_account_id`) 
       references `user_account` (`id`);

    alter table `job` 
       add constraint `FK3rxjf8uh6fh2u990pe8i2at0e` 
       foreign key (`employer_id`) 
       references `employer` (`id`);

    alter table `message` 

       add constraint `FK3ny0h1379q528toyokq81noiu` 
       foreign key (`authenticated_id`) 
       references `authenticated` (`id`);

    alter table `message` 
       add constraint `FKn5adlx3oqjna7aupm8gwg3fuj` 
       foreign key (`message_thread_id`) 
       references `message_thread` (`id`);


    alter table `person` 
       add constraint `FKksb3u7mmp1dgbomtfsy7chbrd` 
       foreign key (`authenticated_id`) 
       references `authenticated` (`id`);

    alter table `person` 
       add constraint `FKjyu7l9bjuiw9l7waxkcce4335` 
       foreign key (`message_thread_id`) 
       references `message_thread` (`id`);

    alter table `provider` 
       add constraint FK_b1gwnjqm6ggy9yuiqm0o4rlmd 
       foreign key (`user_account_id`) 
       references `user_account` (`id`);

    alter table `storage` 
       add constraint `FKbak20tfheetwsi0t2ammfwip0` 
       foreign key (`authenticated_id`) 
       references `authenticated` (`id`);

    alter table `storage` 
       add constraint FK_9x0gqgib0ufkaqlg9a10j24n5 
       foreign key (`user_account_id`) 
       references `user_account` (`id`);


    alter table `worker` 
       add constraint FK_l5q1f33vs2drypmbdhpdgwfv3 
       foreign key (`user_account_id`) 
       references `user_account` (`id`);
