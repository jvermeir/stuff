# Shell

## OhMyZshell

set theme like this in `~/.zshrc`:
    
    ZSH_THEME="jan"
    
### theme 

    ln -s /Users/jan/.oh-my-zsh/themes/jan.zsh-theme /Users/jan/dev/stuff/jan.zsh-theme

## awk 

    cat err.txt|grep JSESSIONID | awk -F' ' '{print $3}'
    
## bash loop over list in a file

    while read CONNECTOR_NAME; do
      if [[ ${index} -eq 0 ]]
      then
        break
      fi
      let index=index-1
    done < all_application.lst        