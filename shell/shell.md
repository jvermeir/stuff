# Shell

## OhMyZshell

set theme like this in `~/.zshrc`:

    ZSH_THEME="jan"

### theme

    ln -s /Users/jan/.oh-my-zsh/themes/jan.zsh-theme /Users/jan/dev/stuff/jan.zsh-theme

configured `aussiegeek` for now

### plugin installation

    brew install thefuck

## fzf

```
git clone --depth 1 https://github.com/junegunn/fzf.git ~/.fzf
~/.fzf/install

source ~/.zshrc
```

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

## enscript

  sudo ruby -e "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/master/install)" < /dev/null 2> /dev/null

  brew install enscript
