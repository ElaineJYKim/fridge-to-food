import React from 'react';
import './CSS/Fridge.scss'
import { Input, Header, List, Icon, Button } from 'semantic-ui-react'


class Fridge extends React.Component {
    constructor(props) {
        super(props);
        this.state = { 
            value: '',
            ingredients: []
        };
        
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }
  
    handleChange(event) {
        this.setState({value: event.target.value});
    }
  
    handleSubmit(event) {
        const newIngredients = [...this.state.ingredients, this.state.value];
        this.setState({ingredients: newIngredients})
        this.setState({value:''})
    
        event.preventDefault();
    }

    removeIngredient(ingredient) {
        const ingredients = this.state.ingredients;

        this.setState({
            ingredients: ingredients.filter((i) => {
                return i !== ingredient
              }),
        });
    }

    handleSearch() {
        const ingredients = this.state.ingredients;
        
        this.props.onChange(ingredients)
    }

    renderButton(ingredients) {
      if (ingredients.length > 0) {
        return (
          <div id='searchButton'>
            <Button floated='right' compact color='green' onClick={() => this.handleSearch()}>레서피 보기</Button>
          </div>
        );
      }
    }

    render() {
        const ingredients = this.state.ingredients
      
        return (
        <div>
          <form onSubmit={this.handleSubmit}><Header as='h3' textAlign='center'>
            냉장고에    <Input class='input' transparent type="text" placeholder='버섯' value={this.state.value} onChange={this.handleChange}/>
            있어요
          </Header>
          </form>

          <List id="checklist">
          {ingredients.map((ingredient) => (
            <List.Item>
              <Icon color='orange' name='minus circle' onClick={() => this.removeIngredient(ingredient)}/>
              <List.Content>
                <List.Header>{ingredient}</List.Header>
              </List.Content>
            </List.Item>
          ))}
          </List>

          {this.renderButton(ingredients)}

        </div>
      );
    }
  }

export default Fridge;
