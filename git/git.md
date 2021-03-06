# Git

## urls
    http://git-scm.com/book/en/v2/
    http://nvie.com/posts/a-successful-git-branching-model/

## basics
    git clone <url>
    git pull
    git add <files of dir>
    git commit -m <omschrijving>
    git push 

## create branch
    git checkout -b <branch name>

## merge branch into master
    <commit on branch>
    git checkout master
    (of git merge <branch name>)
    git merge --no-ff <branch name> 
    git push origin master

## remove branch
    git branch -d "<branch_name>”
    git push origin --delete "<branch_name>"

## merge master into branch
    git checkout master
    git pull
    git checkout <branch>
    git merge master

## Reset to master, discard local changes
    git reset --hard
    git reset HEAD -- <filename>
    git reset <filename> # revert add

## Revert a file to master
    git checkout <filename>

## Reset master to origin
    git switch -C mybranch origin/mybranch

## List branches
    git branch -a

## hotfix branch
    git checkout -b r_20150918_0927_hotfix r_20150918_0927 ## laatste param is basis voor branch
    <changes>
    git add ...
    git commit -m ""
    git push -u origin r_20150918_0927_hotfix
    git tag r_20150918_0927_hotfix_1  ## volgnummer ophogen voor volgende commits.
    git push --tags

## haal wijzigingen binnen van server en stapel je eigen wijzigingen er bovenop
    git pull --rebase

## cherry pick commit
    git cherry-pick <commit hash>

## undo niet gepushte commit
    git reset --soft HEAD~

## haal file weer weg na add
    git reset ../single

## vergelijk twee branches
    git diff <branch1> <branch2> -- <filename>

## list commit on Github
    https://github.com/<repo>/commit/<hash>

## tags
    git for-each-ref --sort=taggerdate --format '%(refname) %(taggerdate)' refs/tags
    git checkout tags/<tag_name> -b <branch_name>

## Remove untracked files and directories
    git clean -fd

## Delete branch
    git push -d origin <branchName>  
    git checkout master
    git branch -D <branchName>   (force delete local unmerged branch)

## Add identity to session
    ssh-add

## Abandon merge
    git merge —abort

## Stash
    git stash
    git stash pop

## Check before merge
    git merge master --no-ff --no-commit
    git merge --abort

## Remotes

    git config --get remote.origin.url
    git remote add origin git@github.com:jvermeir/flink-training-exercises.git
    git remote set-url origin git@github.com:jvermeir/flink-training-exercises.git
    
## Rebase workflow

    git checkout -b b5
    touch b5
    git add b5
    git commit -m "b5"
    git checkout master
    touch m5
    git add m5
    git commit -m m5
    git checkout b5
    git rebase master
    git checkout master
    git merge b5
    git push

## Reset repo to a tag, remove all history past the tag

    git reset --hard basic-app # or use the short commit hash
    git push origin -f
    
## Apply commit from a different branch

    git cherry-pick <commit-id>
         