package com.techmatrix18.service;

import com.techmatrix18.repository.BlogRepository;
import com.techmatrix18.repository.UserRepository;
import org.springframework.stereotype.Service;

/**
 * Blog Service
 *
 * @author Alexander Kuziv <makklays@gmail.com>
 * @company TechMatrix18
 * @version 0.0.1
 * @since 22.01.2026
 */
@Service
public class BlogService {
    private final BlogRepository blogRepository;
    private final UserRepository userRepository;

    public BlogService(UserRepository userRepository, BlogRepository blogRepository) {
        this.userRepository = userRepository;
        this.blogRepository = blogRepository;
    }

    //
}

