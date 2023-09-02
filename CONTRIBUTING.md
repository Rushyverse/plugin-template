# Contributing Guide

This document shows you how to get started contributing to this project.

## Submitting changes

- Fork the repository
- Check out a new branch based and name it to what you intend to do:
    - Example:
      ```shell
      git checkout -b feat/BRANCH_NAME
      git checkout -b fix/BRANCH_NAME
      ```

      If you get an error, you may need to fetch main first by using
      ```shell
      git remote update && git fetch
      ```

    - Use one branch per fix / feature
- Commit your changes
    - Please provide a git message that explains what you've done

    - Please make sure your commits follow the [conventions](https://www.conventionalcommits.org/)

    - Commit to the remote repository

    - Example:

      ```shell
      git commit -am 'feat(command): add a commit message'
      git commit -am 'fix(permission): add a commit message'
      ```

      ```mermaid
        gitGraph
            commit
            commit
            branch feat/filter
            commit
            commit
            checkout main
            commit
            commit
      ```

- Push to the branch
    - Example:

      ```shell
      git push origin BRANCH_NAME
      ```

- Make a pull request
    - Make sure you send the PR to the `main` branch
    - GitHub Actions are watching you!

      ```mermaid
        gitGraph
            commit
            commit
            branch feat/filter
            commit
            commit
            checkout main
            commit
            checkout feat/filter
            merge main id: "merge main into feat/filter"
            checkout main
            merge feat/filter id: "merge feat/filter into main"
      ```

If you follow these instructions, your PR will land pretty safely in the main repository!
